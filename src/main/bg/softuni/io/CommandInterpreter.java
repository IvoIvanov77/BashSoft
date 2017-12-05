package main.bg.softuni.io;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.io.commands.*;
import main.bg.softuni.contracts.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private static final String COMMANDS_PACKAGE = "main.bg.softuni.io.commands.";
    private static final String COMMANDS_LOCATION = "src/main/bg/softuni/io/commands";

    private DirectoryManager ioManager;
    private ContentComparer tester;
    private AsynchDownloader downloadManager;
    private Database studentsRepository;
//    private RepositoryFilter repositoryFilter;
//    private RepositorySorter repositorySorter;

    public CommandInterpreter(DirectoryManager ioManager, ContentComparer tester,
                              AsynchDownloader downloadManager,
                              Database studentsRepository) {
        this.ioManager = ioManager;
        this.tester = tester;
        this.downloadManager = downloadManager;
        this.studentsRepository = studentsRepository;
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    private Executable parseCommand(String line, String[] data, String command) throws IOException {
        File commandsFolder = new File(COMMANDS_LOCATION);
        Executable executable = null;

        for (File file : commandsFolder.listFiles()) {
            if(!file.isFile() || !file.getName().endsWith(".java")){
                continue;
            }
            try{
                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
                Class<Executable> exeClass = (Class<Executable>) Class.forName(COMMANDS_PACKAGE + className);
                if(!exeClass.isAnnotationPresent(Alias.class)){
                    continue;
                }
                Alias alias = exeClass.getAnnotation(Alias.class);
                String value = alias.value();
                if(!value.equalsIgnoreCase(command)){
                    continue;
                }
                Constructor exeCtor = exeClass.getDeclaredConstructor(String.class, String[].class);
                executable = (Executable) exeCtor.newInstance(line, data);
                this.injectDependencies(executable,exeClass);

            }catch (ReflectiveOperationException rfe){
                rfe.printStackTrace();
            }
            
        }

        return executable;
    }

    private void injectDependencies(Executable executable, Class<Executable> exeClass) throws ReflectiveOperationException {

        Field[] exeFields = exeClass.getDeclaredFields();
        for (Field exeField : exeFields) {
            if(!exeField.isAnnotationPresent(Inject.class)){
                continue;
            }
            exeField.setAccessible(true);

            Field[] theseFields = CommandInterpreter.class.getDeclaredFields();
            for (Field thisField : theseFields) {
                if(!thisField.getType().equals(exeField.getType())){
                    continue;
                }
                thisField.setAccessible(true);
                exeField.set(executable, thisField.get(this));
            }
        }
    }


}

package com.design.patterns.corepatterns.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AnotherExample {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutorImpl();
//        commandExecutor.runCommand("ls -ltr");

        CommandExecutor commandExecutorWithProxy = new CommandExecutorProxy("John", "John123");
        commandExecutorWithProxy.runCommand("ls");

        CommandExecutor commandExecutorWithProxyNonAdmin = new CommandExecutorProxy("Harry", "abc");
        commandExecutorWithProxyNonAdmin.runCommand("ls");
    }
}

interface CommandExecutor{
    void runCommand(String cmd);
}

class CommandExecutorImpl implements CommandExecutor{
    @Override
    public void runCommand(String cmd) {
        try {

            Process process = Runtime.getRuntime().exec(cmd);
            InputStream stdIn = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdIn);
            BufferedReader br = new BufferedReader(isr);


            System.out.println("Output from command");
            String s = null;
            while((s=br.readLine())!=null){
                System.out.println(s);
            }

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println("Error from command");
            while((s=stdError.readLine())!=null){
                System.out.println(s);
            }

            System.out.println("'"+cmd+"' command executed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// now to restrict the access and only admin should have full access
class CommandExecutorProxy implements CommandExecutor{
    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd){
        if("John".equals(user) && "John123".equals(pwd)){
            isAdmin = true;
        }
        executor = new CommandExecutorImpl();
    }
    @Override
    public void runCommand(String cmd) {
        if(isAdmin){
            executor.runCommand(cmd);
        }else{
            if(cmd.trim().startsWith("ls")){
                try {
                    throw new Exception("ls command is not allowed for non-admin users");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                executor.runCommand(cmd);
            }
        }
    }
}

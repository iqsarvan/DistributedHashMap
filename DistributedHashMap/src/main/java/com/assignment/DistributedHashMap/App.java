package com.assignment.DistributedHashMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
	public static void main( String[] args ) throws IOException
	{
		DistributedHashMap distributedHashMap = new DistributedHashMap();
		Scanner scanner = new Scanner(System.in);
		String line;
		PrintWriter out = new PrintWriter(System.out);
		headline();usage();
		out.print("> "); out.flush();
		while ((line = scanner.nextLine()) != null) {
			if (line.startsWith("put")) {
				String[] cmd = line.split("\\s+");
				if(cmd.length < 3)
					out.println("<key> <value> not found");
				else 
					distributedHashMap.put(cmd[1], cmd[2]);
			} else if (line.startsWith("get")) {
				String[] cmd = line.split("\\s+");
				if(cmd.length < 2)
					out.println("<key> not found");
				else 
					out.println(distributedHashMap.get(cmd[1]));
			} else if(line.equalsIgnoreCase("display")) {
				out.println(distributedHashMap.toString());
			} else if(line.equalsIgnoreCase("clear")) {
				distributedHashMap.clear();
			} else if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
				break;
			} else {
				usage();
			}
			out.print("> "); out.flush();
		}
		out.close();
		scanner.close();
		distributedHashMap.close();
	}

	public static void headline() {
		System.out.println("DistributedHashMap App");
		System.out.println("======================");
		System.out.println("Open multiple terminals and use following commands");
	}
	public static void usage() {
		System.out.println("Usage: java -cp  DistributedHashMap-0.0.jar " + App.class.getName());
		System.out.println("Commands allowed : ");
		System.out.println("put <key> <value>");
		System.out.println("get <value>");
		System.out.println("clear");
		System.out.println("display");
		System.out.println("quit");
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author Owner
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SpotifyPlaylist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> playlist = new ArrayList<>();
        Stack<ArrayList<String>> undoStack = new Stack<>();
        Stack<ArrayList<String>> redoStack = new Stack<>();

        while (true) {
            System.out.println("\n--- Spotify Playlist ---");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Last Song");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. View Playlist");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add Song
                    undoStack.push(new ArrayList<>(playlist)); // save current state
                    redoStack.clear(); // clear redo history
                    System.out.print("Enter song name: ");
                    String song = sc.nextLine();
                    playlist.add(song);
                    System.out.println("Added: " + song);
                    break;

                case 2: // Remove Last Song
                    if (playlist.isEmpty()) {
                        System.out.println("Playlist is empty. Nothing to remove.");
                    } else {
                        undoStack.push(new ArrayList<>(playlist));
                        redoStack.clear();
                        String removedSong = playlist.remove(playlist.size() - 1);
                        System.out.println("Removed: " + removedSong);
                    }
                    break;

                case 3: // Undo
                    if (undoStack.isEmpty()) {
                        System.out.println("Nothing to undo.");
                    } else {
                        redoStack.push(new ArrayList<>(playlist)); // save current state to redo
                        playlist = new ArrayList<>(undoStack.pop()); // restore from undo
                        System.out.println("Undo successful.");
                    }
                    break;

                case 4: // Redo
                    if (redoStack.isEmpty()) {
                        System.out.println("Nothing to redo.");
                    } else {
                        undoStack.push(new ArrayList<>(playlist));
                        playlist = new ArrayList<>(redoStack.pop());
                        System.out.println("Redo successful.");
                    }
                    break;

                case 5: // View Playlist
                    if (playlist.isEmpty()) {
                        System.out.println("Playlist is empty.");
                    } else {
                        System.out.println("Current Playlist:");
                        for (int i = 0; i < playlist.size(); i++) {
                            System.out.println((i + 1) + ". " + playlist.get(i));
                        }
                    }
                    break;

                case 6: // Exit
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}


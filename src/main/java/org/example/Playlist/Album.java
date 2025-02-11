package org.example.Playlist;

import java.util.ArrayList;
import java.util.LinkedList;
// 10/02/25 Section 10 Exercise 45
public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) == null) {
            this.songs.add(new Song(title, duration));
            return true;
        } else {
            return false;
        }
    }

    private Song findSong(String title) {
        for (Song song : this.songs) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
        if (trackNumber-1 < 0 || trackNumber-1 >= this.songs.size()) {
            return false;
        }

        Song song = this.songs.get(trackNumber);
        playlist.add(song);
        return true;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playlist) {
        Song song = findSong(title);
        if (song == null) {
            return false;
        } else {
            playlist.add(song);
            return true;
        }
    }
}

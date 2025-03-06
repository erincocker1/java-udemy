package org.example.section13.playlist;

import java.util.ArrayList;
import java.util.LinkedList;

// 10/02/25 Section 10 Exercise 45
// Then 13/02/25 Section 13 Exercise 49
public class Album {
    private String name;
    private String artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String title, double duration) {
        return songs.add(new Song(title, duration));
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
        if (trackNumber - 1 < 0 || trackNumber - 1 >= this.songs.songs.size()) {
            return false;
        }

        Song song = this.songs.songs.get(trackNumber - 1);
        playlist.add(song);
        return true;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playlist) {
        Song song = songs.findSong(title);
        if (song == null) {
            return false;
        } else {
            playlist.add(song);
            return true;
        }
    }

    static public class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            this.songs = new ArrayList<Song>();
        }

        private boolean add(Song song) {
            if (findSong(song.getTitle()) != null) {
                return false;
            }
            this.songs.add(song);
            return true;
        }

        private Song findSong(String title) {
            for (Song song : this.songs) {
                if (song.getTitle().equals(title)) {
                    return song;
                }
            }
            return null;
        }

        private Song findSong(int trackNumber) {
            if (trackNumber - 1 < 0 || trackNumber - 1 >= this.songs.size()) {
                return null;
            }
            return this.songs.get(trackNumber);
        }
    }
}

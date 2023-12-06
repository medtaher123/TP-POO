class Song {
	String title;
	String artist;
	int length; // in seconds

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getLength() {
		return length;
	}

	public Song(String title, String artist, int length) {
		this.title = title;
		this.artist = artist;
		this.length = length;
	}

	public void playSong() {
		System.out.println("Playing: " + title + " by " + artist);
	}

	public int getSongLength() {
		return length;
	}

}
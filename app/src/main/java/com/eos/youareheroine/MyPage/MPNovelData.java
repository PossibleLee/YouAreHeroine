package com.eos.youareheroine.MyPage;

public class MPNovelData {
    public int id;
    public String title;
    public int watcher;
    public int comment;
    public String date;
    public String image;
    public boolean isEnd;

    public MPNovelData(int id, String title, int watcher, int comment, String date, String img, boolean isEnd) {
        this.id = id;
        this.title = title;
        this.watcher = watcher;
        this.comment = comment;
        this.date = date;
        this.image = img;
        this.isEnd = isEnd;
    }
}

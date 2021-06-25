package com.example.note2;

public abstract class Note
{
    int ID;
    String username;
    String TieuDe;
    String NoiDung;
    String Nhan;
    public Note(){};
    public Note(int ID, String username, String tieuDe, String noiDung, String nhan) {
        this.ID = ID;
        this.username = username;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        Nhan = nhan;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString()
    {
        return ID +"";
    }
}

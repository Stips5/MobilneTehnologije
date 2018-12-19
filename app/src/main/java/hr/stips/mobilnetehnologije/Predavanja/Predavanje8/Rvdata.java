package hr.stips.mobilnetehnologije.Predavanja.Predavanje8;

class Rvdata {//klasa za spremanje podataka iz JSON-a

    private int id;
    private String name;
    private String img; //url na sliku

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}

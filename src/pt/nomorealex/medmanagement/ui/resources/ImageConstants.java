package pt.nomorealex.medmanagement.ui.resources;

public enum ImageConstants {
    ADDPERSON("add-friend.png"),
    DATABASE("base-de-dados.png"),
    GREENPLUS("greenPlus.png"),
    PILLSSCHEDULE("medicina.png"),
    PILLS("pills.png"),
    PLUSSIGN("plus.png"),
    SPLUSSIGN("plus1.png");
    private String name;
    ImageConstants(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}

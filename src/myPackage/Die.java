package myPackage;

public class Die implements DieIntf, Comparable<Die>{

    private int image,value;

    public Die(int value) {
        this.value = value;
    }

    public int getDieImage(){
        return image;
    }

    public void setImage(int i){
        image = i;
    }
    public void setValue(int v){
        value = v;
    }
    public int getValue(){
        return value;
    }

    public int compareTo(Die T){
        if(T.value == this.value) return 0;
        else return T.value > this.value ? 1 : -1;
    }
}

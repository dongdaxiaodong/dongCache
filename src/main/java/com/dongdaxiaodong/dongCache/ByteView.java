package com.dongdaxiaodong.dongCache;

import java.util.Arrays;

public class ByteView {
    byte[] b;

    ByteView(){};
    public ByteView(byte[] b){
        this.b = b;
    }

    public int Len(){
        return b.length;
    }

    public byte[] ByteSlice(){
        return cloneBytes(b);
    }

    public String String() {
        return new String(b);
    }

    public byte[] cloneBytes(byte[] oldB){
        return  Arrays.copyOf(oldB,oldB.length);
    }
}
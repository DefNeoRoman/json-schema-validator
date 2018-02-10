package com.groupon.maven.plugin;

public class Faktorial {
    //Пример рекурсии
    public static void main(String[] args) {
        Faktorial fakt = new Faktorial();
        System.out.println(fakt.factorial(5));
    }
    public Integer factorial(Integer num){
        while(num != 0){
            num--;
            System.out.println(num);
        }
        if(num == 0) return 0;
        return factorial(num);
    }
}

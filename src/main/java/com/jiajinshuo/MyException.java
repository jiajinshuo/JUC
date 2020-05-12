package com.jiajinshuo;

/**
 * @author jiajinshuo
 * @create 2020-04-13 20:06
 */
public class MyException extends RuntimeException {
    public MyException( Integer id){
        super("user not exit");
        this.id = id;
    }
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
package com.example.endofterm.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer sum;
    private Integer identity;
    private Integer noBack;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, Integer sum, Integer identity, Integer noBack) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sum = sum;
        this.identity = identity;
        this.noBack = noBack;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return sum
     */
    public Integer getSum() {
        return sum;
    }

    /**
     * 设置
     * @param sum
     */
    public void setSum(Integer sum) {
        this.sum = sum;
    }

    /**
     * 获取
     * @return identity
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * 设置
     * @param identity
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * 获取
     * @return noBack
     */
    public Integer getNoBack() {
        return noBack;
    }

    /**
     * 设置
     * @param noBack
     */
    public void setNoBack(Integer noBack) {
        this.noBack = noBack;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", sum = " + sum + ", identity = " + identity + ", noBack = " + noBack + "}";
    }
}

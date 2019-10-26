package com.company;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("********************数据库操作系统**********************");
        while (true){
            System.out.println("要干啥？(1:查询,2:写入,3:删除,4:返回)");
            Scanner in = new Scanner(System.in);
            String result = in.next();
            if (result.equals("1")) {
                login();
            } else if (result.equals("2")) {
                insert();
            } else if (result.equals("3")) {
                delete();
            } else if (result.equals("4")) {
                System.out.println("Bye!");
              break;
            }
        }
    }
    private static void login() {
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/text", "yu", "021106.w");
            Statement sql = con1.createStatement();
            String a="select * from test;";
            PreparedStatement ps=con1.prepareStatement(a);
            ResultSet rs=ps.executeQuery();
            System.out.println("****************读取SQL内容******************");
            while(rs.next()){
                String id=rs.getString(1);
                String name=rs.getString(2);
                System.out.print("*id"+" * "+id+" ");
                System.out.println(" * "+name+"**");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private  static void insert()  {
        System.out.println("输入id：");
        Scanner in=new Scanner(System.in);
        int id=in.nextInt();
        System.out.println("输入name:");
        Scanner in2=new Scanner(System.in);
        String name=in2.next();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/text", "yu", "021106.w");
            Statement sql = con1.createStatement();
            String a = "insert into test values(?,?);";
            PreparedStatement ps = con1.prepareStatement(a);
            ps.setInt(1,id);
            ps.setString(2,name);
            int row=ps.executeUpdate();
            if(row>=0){
                System.out.println("添加成功,返回"+row+"条数据！");
            }else{
                System.out.println("添加失败,请检查输入内容！");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(){
        System.out.println("输入要删除的id:");
        Scanner in=new Scanner(System.in);
        int id=in.nextInt();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/text", "yu", "021106.w");
            Statement sql = con1.createStatement();
            String a="delete from text.test where id=?;";
            PreparedStatement ps=con1.prepareStatement(a);
            ps.setInt(1,id);
            int row=ps.executeUpdate();
            if(row>0){
                System.out.println("删除成功,返回"+row+"条数据！");
            }else{
                System.out.println("删除失败,没有该项目！");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

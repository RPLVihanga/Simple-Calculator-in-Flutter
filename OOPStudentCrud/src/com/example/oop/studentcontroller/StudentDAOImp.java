/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.oop.studentcontroller;

import com.example.oop.model.student;
import com.example.oop.studentdb.studentdb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class StudentDAOImp implements studentDAO{

    @Override
    public void save(student students) {
        try{
         Connection con = studentdb.getConnection();
         String sql = "INSERT INTO students(fname,course,fee)values (?,?,?)";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, students.getFname());
         ps.setString(2, students.getCourse());
         ps.setInt(3, students.getFee());
         ps.executeUpdate();
         JOptionPane.showMessageDialog(null,"Saved!");
    }catch(Exception e){
            e. printStackTrace();
            JOptionPane.showMessageDialog(null,"Error");
            }
       
    }

    @Override
    public void update(student students) {
        try{
            Connection con = studentdb.getConnection();
            String sql = "UPDATE students SET fname=?,course=?,fee=? WHERE id =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, students.getFname());
            ps.setString(2, students.getCourse());
            ps.setInt(3, students.getFee());
            ps.setInt(4, students.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Update!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error");
        } catch (Exception ex) {
            Logger.getLogger(StudentDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(student students) {
        try{
            Connection con = studentdb.getConnection();
            String sql = "DELETE from students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, students.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error");
        } catch (Exception ex) {
            Logger.getLogger(StudentDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public student get(int id) {
        student st = new student();
        try{
            Connection con = studentdb.getConnection();
            String sql = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                st.setId(rs.getInt("id"));
                st.setFname(rs.getString("fname"));
                st.setCourse(rs.getString("course"));
                st.setFee(rs.getInt("fee"));
        }
            
    }catch (Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,"Error");
    }
        return st;
    }    
    @Override
    public List<student> list() {
        List<student> list = new ArrayList<student>();
        try{
            Connection con = studentdb.getConnection();
            String sql ="SELECT * FROM students ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                student st = new student();
                st.setId(rs.getInt("id"));
                st.setFname(rs.getString("fname"));
                st.setCourse(rs.getString("course"));
                st.setFee(rs.getInt("fee"));
                
                list.add(st);
            }
        } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null,"Error");
        }
        return list;
    }
    
    }

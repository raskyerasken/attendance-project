/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.MockData;
import GUI.Model;
import java.text.ParseException;

/**
 *
 * @author jacob
 */
public class BLLManager {
    MockData mockData=new MockData();
    public void add(Model model) throws ParseException {
        mockData.add(model);
     }
    
}

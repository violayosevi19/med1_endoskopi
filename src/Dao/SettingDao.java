/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.SettingModel;

/**
 *
 * @author HP
 */
public interface SettingDao {
    public void insert(SettingModel settingModel) throws Exception;
    public void ClearAll() throws Exception;
}

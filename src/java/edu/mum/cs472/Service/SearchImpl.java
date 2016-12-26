/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs472.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bijayregmi
 */
public class SearchImpl implements SearchInterface {

    @Override
    public String Search(String str) {

        Connection conn = DbConnection.db_Connect();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = null;
        String query = "SELECT wordtype,definition from entries where word=?;";

        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, str);
            rs = stmt.executeQuery();

            result = convertToJSON(rs).toString();

        } catch (JSONException e) {
        } catch (SQLException ex) {
            Logger.getLogger(SearchImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConnection.db_Close(conn);
        }
        return result;
    }

    private JSONArray convertToJSON(ResultSet resultSet) throws SQLException, JSONException {
        JSONArray jsonArray = new JSONArray();

        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
            }
            jsonArray.put(obj);
        }

        return jsonArray;
    }

}

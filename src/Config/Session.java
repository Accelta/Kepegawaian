/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

/**
 *
 * @author Axel
 */
public class Session {

    /**
     * @return the nama
     */
    public static String getNama() {
        return nama;
    }

    /**
     * @param aNama the nama to set
     */
    public static void setNama(String aNama) {
        nama = aNama;
    }

    /**
     * @return the id_user
     */
    public static int getId_user() {
        return id_user;
    }

    /**
     * @param aId_user the id_user to set
     */
    public static void setId_user(int aId_user) {
        id_user = aId_user;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the level
     */
    public static String getLevel() {
        return level;
    }

    /**
     * @param aLevel the level to set
     */
    public static void setLevel(String aLevel) {
        level = aLevel;
    }

    /**
     * @return the pass
     */
  
    private  static int id_user;
    private static String user;
    private static String level;
  private static String nama;
    
}

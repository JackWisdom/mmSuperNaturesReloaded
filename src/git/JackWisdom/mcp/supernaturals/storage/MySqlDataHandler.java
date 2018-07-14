package git.JackWisdom.mcp.supernaturals.storage;

import com.google.gson.Gson;
import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import java.sql.*;
import java.util.HashSet;
import java.util.UUID;

public class MySqlDataHandler extends SNDataHandler {
    String url=SNConfigHandler.sqlUrl;
    String prefix=SNConfigHandler.sqlPrefix;
    String playerTable=prefix+"snplayer";
    String teleTable=prefix+"snteleport";
    Connection connection;
    private Connection createConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
    public MySqlDataHandler() throws SQLException {
        connection=createConnection(url);
        createTables();
    }
    private void createTables(){

        String playerSql="CREATE TABLE "+playerTable+"(\n" +
                "   uuid char(36) not null primary key,\n" +
                "   type varchar(10) not null default 'HUMAN',\n" +
                "   oldtype varchar(10) not null default 'HUMAN',\n" +
                "   trucetimer int not null  default 0,\n" +
                "   truce boolean not null  default true,\n" +
                "   power double not null default 0,\n" +
                "   oldpower double not null default 0,\n" +
                "   hunterapp varchar(16)\n" +
                "   \n" +
                ")";
        String teleportSql="CREATE TABLE "+teleTable+"(\n" +
                "  uuid char(36) not null primary key,\n" +
                "  world varchar(10),\n" +
                "  x double,\n" +
                "  y double,\n" +
                "  z double\n" +
                ")";
        try{ Statement statement=connection.createStatement();
            statement.execute(playerSql);
            statement.execute(teleportSql);
            statement.close();
        }catch (Exception e){
            SupernaturalsPlugin.log(e.getMessage());
        }
    }
    private void check(){
        try {
            if(connection==null||connection.isClosed()){
            connection=createConnection(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String getPlayerData(String uid){
        return "select * from "+playerTable+" where uuid='"+uid+"'";
    }

    private String getVampireLocationSql(String uid){
        return "select * from "+teleTable+" where uuid='"+uid+"'";
    }
    @Nullable
    private Location loadLocationFromResultSet(ResultSet set){
        Location loc=null;
        try {
           loc =new Location(Bukkit.getWorld(set.getString("world")),set.getDouble("x"),set.getDouble("y"),set.getDouble("z"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }
    @Override
    public SuperNPlayer load(UUID uuid) {
        check();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(getPlayerData(uuid.toString()));
            if(!set.next()){
            return new SuperNPlayer(uuid);
            }else {
                SuperNPlayer np=new SuperNPlayer();
                np.uuid=UUID.fromString(set.getString("uuid"));
                np.type=SuperType.valueOf(set.getString("type"));
                np.oldType=SuperType.valueOf(set.getString("oldtype"));
                np.superPower=set.getDouble("power");
                np.oldPower=set.getDouble("oldpower");
                String hunterapp=set.getString("hunterapp");
                if(hunterapp!=null){
                    Gson json=new Gson();
                    HashSet<SuperType> types=json.fromJson(hunterapp,HashSet.class);
                    np.hunterApp=types;
                }
                ResultSet set0=statement.executeQuery(getVampireLocationSql(uuid.toString()));
                if(set0.next()){
                    np.teleport=loadLocationFromResultSet(set0);
                }
                statement.close();
                return np;
            }
        }catch (Exception e){
            SupernaturalsPlugin.log("an error happened while loading "+uuid);
        }
        return null;
    }
    private void updatePlayerData(SuperNPlayer player) throws SQLException {
        Statement statement=connection.createStatement();
        statement.execute(deleteData(playerTable,player.uuid.toString()));
        statement.close();
        //删除旧数据完成 开始输入新的
      String url="insert into "+playerTable+"(uuid,type,oldtype,trucetimer,truce,power,oldpower,hunterapp) values(?,?,?,?,?,?)";
      PreparedStatement ps=connection.prepareStatement(url);
      ps.setString(1,player.uuid.toString());
      ps.setString(2,player.type.name());
      ps.setString(3,player.oldType.name());
      ps.setDouble(4,player.superPower);
      ps.setDouble(5,player.oldPower);
      HashSet<SuperType> app=player.hunterApp;
      ps.setString(6,new Gson().toJson(app));
      ps.execute();
      ps.close();
    }
    private void updateTeleport(SuperNPlayer player) throws SQLException {
    if(!player.isVampire()||player.teleport==null){
        return ;
    }
    String uid= player.uuid.toString();
        Statement statement=connection.createStatement();
        statement.execute(deleteData(teleTable,uid));
        statement.close();

        Location loc=player.getTeleport();
        String url="insert into "+teleTable+"(uuid,world,x,y,z) values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(url);
        ps.setString(1,uid);
        ps.setString(2,player.teleport.getWorld().getName());
        ps.setDouble(3,player.teleport.getX());
        ps.setDouble(4,player.teleport.getY());
        ps.setDouble(5,player.teleport.getZ());
    }
    private String deleteData(String table,String uid){
        return "delete from "+table+" where uuid='"+uid+"'";
    }
    @Override
    public void save(SuperNPlayer player) {
        check();
        try {
            updatePlayerData(player);
            updateTeleport(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

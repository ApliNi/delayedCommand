package aplini.delayedcommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class DelayedCommand extends JavaPlugin {
    private final JavaPlugin plugin = this;

//    @Override
//    public void onEnable() {
//        // Plugin startup logic
//
//    }
//
//    @Override
//    public void onDisable() {
//        // Plugin shutdown logic
//    }

    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {

        // 没有权限
        if(! sender.hasPermission("delayedCommand.use")){
            return false;
        }

        // 参数不足
        if(args.length < 3){
            sender.sendMessage("[delayedCommand] 延迟执行指令");
            sender.sendMessage("  - /cdelay <模式> <毫秒> <指令>");
            sender.sendMessage("    - 模式: [player: 为自己执行, console: 使控制台执行]");
            sender.sendMessage("    - 毫秒: (ms/1000*20) 向上取整, 延迟指定毫秒数后运行指令");
            sender.sendMessage("    - 指令: 不带斜杠的完整指令(可包含参数)");
            return false;
        }

        // 从毫秒数获取tick
        long tick = (long) Math.abs(Math.ceil((float) Integer.parseInt(args[1]) / 1000 * 20));

        // 获取指令
        StringBuilder command = new StringBuilder();
        for(int i = 2; i < args.length; i++){
            command.append(args[i]).append(" ");
        }

        // 模式
        if(args[0].equals("player")){
            getServer().getScheduler().runTaskLater(this.plugin,
                    () -> Bukkit.getServer().dispatchCommand(sender, String.valueOf(command)),
                    tick);
        }else if(args[0].equals("console")){
            getServer().getScheduler().runTaskLater(this.plugin,
                    () -> Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), String.valueOf(command)),
                    tick);
        }

        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("player"); // 为自己执行
            list.add("console"); // 使控制台执行
            return list;
        }
        else if(args.length == 2){
            List<String> list = new ArrayList<>();
            list.add("50");
            list.add("1000");
            return list;
        }
        return null;
    }
}

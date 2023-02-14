# delayedCommand
为玩家或控制台创建延时运行的指令, 用于解决一些UI冲突

```
[delayedCommand] 延迟执行指令
  - /cdelay <模式> <毫秒> <指令>
    - 模式: [player: 为自己执行, console: 使控制台执行]
    - 毫秒: (ms/1000*20) 向上取整, 延迟指定毫秒数后运行指令
    - 指令: 不带斜杠的完整指令(可包含参数)
    - 权限: delayedCommand.use
```
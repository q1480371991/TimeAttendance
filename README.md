# 签到系统后端api文档

## 签到系统

### API

接口返回值统一格式

```
{
    "flag": xxx,
    "msg": xxx,
    "recordtime":xxx,
    "data": xxx,
    "code": xxx
}
```





#### 签到

GET:/login?studentid=xxxx



Response

```
{
    "flag": true,
    "msg": "2100301419签到成功",
    "recordtime": null,
    "data": {
        "name": "tao",
        "id": 3,
        "endTime": "2022-08-31 17:58:55",
        "startTime": "2022-09-01 10:16:40",
        "status": 1,
        "weekTime": 1.0,
        "totalTime": 0.0,
        "studentid": "2100301419"
    },
    "code": 20011
}
```



如果重复签到

```
{
    "flag": false,
    "msg": "2100301419已签到",
    "recordtime": null,
    "data": {
        "name": "tao",
        "id": 3,
        "endTime": "2022-08-31 17:58:55",
        "startTime": "2022-09-01 10:16:40",
        "status": 1,
        "weekTime": 1.0,
        "totalTime": 0.0,
        "studentid": "2100301419"
    },
    "code": 20050
}
```



#### 签退

GET:/logout?studentid=xxx



Response

```
{
    "flag": true,
    "msg": "2100301419签退成功",
    "recordtime": "0.03",
    "data": {
        "name": "tao",
        "id": 3,
        "endTime": "2022-09-01 10:18:15",
        "startTime": "2022-09-01 10:16:40",
        "status": 0,
        "weekTime": 1.03,
        "totalTime": 0.03,
        "studentid": "2100301419"
    },
    "code": 20021
}
```

如果没有签到

```
{
    "flag": false,
    "msg": "2100301419未签到",
    "recordtime": null,
    "data": {
        "name": "tao",
        "id": 3,
        "endTime": "2022-09-01 10:18:15",
        "startTime": "2022-09-01 10:16:40",
        "status": 0,
        "weekTime": 1.03,
        "totalTime": 0.03,
        "studentid": "2100301419"
    },
    "code": 20060
}
```



#### 获取排行榜前三

GET：/topthree?grade=xxx

xxx为年级，20或21



Response

```
{
    "flag": true,
    "msg": null,
    "recordtime": null,
    "data": [
        {
            "name": "lin",
            "id": 2,
            "endTime": "2022-08-31 17:58:51",
            "startTime": "2022-08-31 22:03:40",
            "status": 1,
            "weekTime": 56.97,
            "totalTime": 56.97,
            "studentid": "2100301418"
        },
        {
            "name": "chen",
            "id": 1,
            "endTime": "2022-08-31 17:58:34",
            "startTime": "2022-08-31 22:04:48",
            "status": 0,
            "weekTime": 2.45,
            "totalTime": 2.45,
            "studentid": "2100301417"
        },
        {
            "name": "tao",
            "id": 3,
            "endTime": "2022-09-01 10:18:15",
            "startTime": "2022-09-01 10:16:40",
            "status": 0,
            "weekTime": 1.03,
            "totalTime": 0.03,
            "studentid": "2100301419"
        }
    ],
    "code": null
}
```

#### 获取当前在线用户

GET：/showonline

```
{
    "flag": true,
    "msg": null,
    "recordtime": null,
    "data": [
        {
            "name": "lin",
            "id": 2,
            "endTime": "2022-08-31 17:58:51",
            "startTime": "2022-08-31 22:03:40",
            "status": 1,//0不在线，1在线
            "weekTime": 56.97,
            "totalTime": 56.97,
            "studentid": "2100301418"
        }
    ],
    "code": null
}
```



#### 获取某个人所有的签到记录

GET：/mytimerecored?userid=xxx

xxx为id不是studentid



Response

```
{
    "flag": true,
    "msg": null,
    "recordtime": null,
    "data": [
        {
            "id": 143,
            "userid": 1,
            "duration": 123.5,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 17:58:34",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 139,
            "userid": 1,
            "duration": 121.17,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:38:43",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 135,
            "userid": 1,
            "duration": 121.12,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:35:46",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 130,
            "userid": 1,
            "duration": 120.97,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:27:07",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 126,
            "userid": 1,
            "duration": 120.93,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:24:18",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 123,
            "userid": 1,
            "duration": 120.89,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:21:52",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 121,
            "userid": 1,
            "duration": 120.84,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:19:17",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 119,
            "userid": 1,
            "duration": 120.7,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 15:10:54",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 118,
            "userid": 1,
            "duration": 119.37,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 13:50:51",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 117,
            "userid": 1,
            "duration": 119.25,
            "status": 2,
            "reporterid": "2100301418",
            "endTime": "2022-08-31 13:43:28",
            "startTime": "2022-08-26 14:28:43"
        },
        {
            "id": 114,
            "userid": 1,
            "duration": 36.45,
            "status": 0,
            "reporterid": "0",
            "endTime": "2022-08-25 23:30:00",
            "startTime": "2022-08-24 11:02:45"
        },
        {
            "id": 111,
            "userid": 1,
            "duration": 0.07,
            "status": 0,
            "reporterid": "0",
            "endTime": "2022-08-23 23:30:00",
            "startTime": "2022-08-23 23:25:30"
        },
        {
            "id": 85,
            "userid": 1,
            "duration": 0.35,
            "status": 1,
            "reporterid": "0",
            "endTime": "2022-08-21 11:23:07",
            "startTime": "2022-08-21 11:02:03"
        },
        {
            "id": 68,
            "userid": 1,
            "duration": 0.0,
            "status": 1,
            "reporterid": "0",
            "endTime": "2022-08-12 22:50:15",
            "startTime": "2022-08-12 22:50:06"
        },
        {
            "id": 66,
            "userid": 1,
            "duration": 2.1,
            "status": 1,
            "reporterid": "0",
            "endTime": "2022-08-12 22:48:34",
            "startTime": "2022-08-12 20:42:35"
        },
        {
            "id": 59,
            "userid": 1,
            "duration": 0.2,
            "status": 1,
            "reporterid": "0",
            "endTime": "2022-08-12 20:42:34",
            "startTime": "2022-08-12 20:30:34"
        },
        {
            "id": 55,
            "userid": 1,
            "duration": 0.13,
            "status": 1,
            "reporterid": "0",
            "endTime": "2022-08-12 20:30:00",
            "startTime": "2022-08-12 20:22:07"
        },
        {
            "id": 44,
            "userid": 1,
            "duration": 0.15,
            "status": 1,
            "reporterid": "0",
            "endTime": "2022-08-10 23:27:04",
            "startTime": "2022-08-10 23:18:07"
        },
        {
            "id": 27,
            "userid": 1,
            "duration": 114.47,
            "status": 0,
            "reporterid": "0",
            "endTime": "2022-07-12 10:44:31",
            "startTime": "2022-07-07 16:16:32"
        }
    ],
    "code": null
}
```



#### 导入用户

POST：/adduser



#### 其他

如果学号不正确

```
{
    "flag": false,
    "msg": "学号221不存在",
    "recordtime": null,
    "data": null,
    "code": 20070
}
```





## 聊天室

### websocket心跳包机制

#### **1.心跳包的意义：**

在使用websocket的过程中，有时候会遇到网络断开的情况，但是在网络断开的时候服务器端并没有触发onclose的事件。
这样会有：服务器会继续向客户端发送多余的链接，并且这些数据还会丢失。
所以就需要一种机制来检测客户端和服务端是否处于正常的链接状态。
因此就有了websocket的心跳了，还有心跳，说明还活着，没有心跳说明已经挂掉了。



#### **2、实现心跳检测的思路：**

通过setInterval定时任务每个3秒钟调用一次reconnect函数
reconnect会通过socket.readyState来判断这个websocket连接是否正常
如果不正常就会触发定时连接，每4s钟重试一次，直到连接成功
如果是网络断开的情况下，在指定的时间内服务器端并没有返回心跳响应消息，因此服务器端断开了。
服务断开我们使用ws.close关闭连接，在一段时间后，可以通过 onclose事件监听到。



通过**setTimeout**延时器，延时10s发送一次心跳，后端检测到心跳会反馈一个心跳心跳反馈，前端接收到心跳反馈后重置延时器



前端没接收到消息都会重置心跳检测的延时器



如果60s内没有检测到心跳反馈则重连



心跳检测机制未写完 8.31

已加入心跳检测机制 9.2






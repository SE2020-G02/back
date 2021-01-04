# 接口文档

## 用户接口

### 登录请求

`URL：http://localhost:8081/account/login`

```JSON
requestbody：
{
    "accountNickname":"hahaha",
    "accountPwd":"pwd"
}
```

### 注册请求

`URL：http://localhost:8081/account/register`

```json
requestbody：
{
    "accountNickname":"test",
    "accountPhone":"18888888888", 
    "accountPwd":"pwd"
}
```



### 改密请求

`URL：http://localhost:8081/account/updatePwd`

```json
requestbody：
{
    "accountNickname":"dhg",
    "accountOldPwd":"pwd", 
    "accountNewPwd":"123456"
}
```





### 获取用户公共信息请求

`URL：http://localhost:8081/account/public/message`

```json
requestbody：
{
    "accountId":"1"
}
```





### 重置密码请求

`URL：http://localhost:8081/account/reset`

```json
requestbody：
{
    "accountNickname":"test",
    "accountPhone":"18888888888"
}
```



## 博客接口

### 获取第一个博客详细信息请求

`URL：http://localhost:8081/blogs/display`

```json
requestbody：
{
    "problemId":"0001",
    "blogId":"0000"
}
or
{
    "problemId":"0001",
    "blogId":"0001"
}
```

### 获取所有博客详细信息请求

`URL：http://localhost:8081/blogs/all`

```json
requestbody：
{
    "problemId":"0001"
}
```

### 新建博客请求

`URL：http://localhost:8081/blogs/creat`

```json
requestbody：
{
    "accountId":"3",
    "problemId":"0001",
    "blogMessage":"postman test"
}
```





## 题目接口

### 获取题目page请求

`URL：http://localhost:8081/problem/page`

```json
{
    "accountId":"1",
    "problemLevel":1, 
    "problemCount":2
}
```



### 新增题目请求

`URL：http://localhost:8081/problem/insert/problems`

```json
requestbody：
{
    "Path":"C:XXXXXXXX",
    "problemLevel":1
}
```



### 获取题目信息请求

`URL：http://localhost:8081/problem/message`

```json
requestbody：
{
    "problemId":"0523"
}
```



### 获取每日一题信息请求

`URL：http://localhost:8081/problem/random`

```json
requestbody：
{
    "accountId":"1"
}
```



## 天梯接口

### 获取天梯第一页（每页两人）请求

`URL：http://localhost:8081/rate/limit`

```json
requestbody：
{
    "rateCount":2,
    "ratePage":1
}
```

### 根据每页人数获取页数请求

`URL：http://localhost:8081/rate/pageCount`

```json
requestbody：
{
    "rateCount":2
}
```

### 获取天梯页面静态信息请求

`URL：http://localhost:8081/rate/static`

```json
requestbody：
{
    
}
```



## 悬赏接口

### 获取悬赏页请求

`URL：http://localhost:8081/reward/page`

```json
requestbody：
{
    "rewardState":"已做出",
    "rewardCount":3
}
or
{
    "rewardState":"未做出",
    "rewardCount":3
}
```



### 判断悬赏题目是否可解请求

`URL：http://localhost:8081/reward/judge`

```json
requestbody：
{
   "problemPanes":"000006010004012007519070026140069750750000089092150034930080245400720900020900000"
}
```



### 新建悬赏请求

`URL：http://localhost:8081/reward/judge`

```json
requestbody：
{
   这个超级复杂，用的时候再说吧，还没测，太复杂了
}
```





## 房间接口

### 创建房间请求

`URL：http://localhost:8081/room/creat`

```json
requestbody：
{
    "accountId":"1",
    "roomStartTime":"2020-01-03 14:30:00",
    "roomRunningTime":30,
    "roomLevel":1
}
```



### 根据等级获取房间信息请求

`URL：http://localhost:8081/room/page`

```json
requestbody：
{
    "roomLevel":1,
    "accountId":"1"
}
```



### 根据房间状态获取房间信息请求

`URL：http://localhost:8081/room/state`

```json
requestbody：
{
    "accountId":"1",
    "problemState":"待开始"
}
```



## 房间人员接口

### 加入房间请求

`URL：http://localhost:8081/roomMember/join`

```json
requestbody：
{
    "roomId":1,
    "accountID":"1"
}
```



### 退出房间请求

`URL：http://localhost:8081/roomMember/quit`

```json
requestbody：
{
    "roomId":1,
    "accountID":"2"
}
```



### 比赛排行耪刷新请求

`URL：http://localhost:8081/roomMember/getMessage`

```json
requestbody：
{
    "roomId":1,
    "accountID":"2",
    "timecost":0,
    "percentage":23.34
}
```



### 比赛排行耪刷新请求

`URL：http://localhost:8081/roomMember/room/message`

```json
requestbody：
{
    "roomId":1,
    "accountID":"2",
    "timecost":0,
    "percentage":23.34
}
```





## 通过接口

### 获取题目通过rank请求

`URL：http://localhost:8081/solve/rank`

```json
requestbody：
{
    "problemId":"0523"
}
```



### 提交题目答案请求

`URL：http://localhost:8081/solve/submit`

```json
requestbody：
{
    "problemId":"0001",
    "accountId":"1",
    "problemPanes":[
                [4,6,9,7,5,1,2,3,8],
                [7,1,3,6,8,2,5,4,9],
                [8,5,2,4,3,9,7,1,6],
                [9,3,8,1,6,5,4,7,2],
                [6,2,4,8,7,3,1,9,5],
                [1,7,5,2,9,4,6,8,3],
                [5,9,7,3,4,6,8,2,1],
                [3,4,1,5,2,8,9,6,7],
                [2,8,6,9,1,7,3,5,4]
            ],
            "solveCostTime":30
}
```



### 判断题目是否已做请求

`URL：http://localhost:8081/solve/judge`

```json
requestbody：
{
    "problemId":"0523",
    "accountId":"2"
}

response：
{
    "msg": "查询成功",
    "code": 0,
    "data": true
}
```

















​	



### 请求

`URL：http://localhost:8081/reward`

```json
requestbody：
{
    "accountNickname":"test",
    "accountPhone":"18888888888", 
    "accountPwd":"pwd"
}
```






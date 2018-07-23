# 解决监听warning问题
```js
warning: possible EventEmitter memory leak detected. 11 listeners added. Use emitter.setMaxListeners() to increase limit.
```
nodejs注册过多的监听函数，解决方法
```js
    const EventEmitter = require('events');
    const myEmitter = new EventEmitter();
    myEmitter.setMaxListeners(0);
```
0为不报警，最好设置上限

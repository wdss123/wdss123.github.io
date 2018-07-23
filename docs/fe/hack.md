 # hack
 #### 深拷贝
 JSON.parse(JSON.stringify(test));
深拷贝 字符串是新地址 parse对象
#### input事件以及中文输入法的处理
[https://www.cnblogs.com/jesse007/p/5627167.html](https://www.cnblogs.com/jesse007/p/5627167.html)
采用compositionstart和compositionend来捕获IME（input method editor）的启动和关闭事件

#### JavaScript复制内容到剪贴板

采用[clipboard.js](http://clipboardjs.com/)或者原生[document.execCommand()](https://juejin.im/post/5a94f8eff265da4e9b593c29?utm_source=gold_browser_extension)


#### 不用getElementById获取元素
现代浏览器可以直接id获取元素
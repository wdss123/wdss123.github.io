# 使用nuxt的ssr渲染
组件没法用asyncData渲染(官网文档)

> Sometimes you just want to fetch data and pre-render it on the server without using a store. asyncData is called every time before loading the component (only for pages components). It can be called server-side or before navigating to the corresponding route. This method receives the context as the first argument, you can use it to fetch some data and Nuxt.js will merge it with the component data.


说明nuxt是通过vue提供的asyncData来渲染也就是通过路由来抓取是否ssr，所以组件不在页面路由级别无法用ssr渲染页面中组件
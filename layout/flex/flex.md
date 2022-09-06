## container 容器组件
#### 容器属性CSS 
display、flex-direction、justify-content、align-items、flex-wrap、align-content、flex-flow

##### 当行主侧轴对齐方式
display:flex;
flex-drecion:row\column;
flex-flow: row nowrap;
当行对其方式
justify-content:baseline\flex-start\flex-end\center\space-between\space-around\space-evenly
align-items:flex-start\flex-end\center\strech
注意点：单行align-items:strech拉伸，只在侧轴没有高度或者没有长度时生效!!
align-items:strech

##### 多行主侧轴对齐方式

flex-direction:row;
flex-wrap:wrap;
flex-flow:row wrap;


多行align-items:strech拉伸，只在侧轴没有高度或者没有长度时生效，平分整个侧轴的高度
align-items:strech


#### 元素翻转，调换顺序
flex-direction:row-reverse






#### 子项css属性
 order、align-self、flex


/*React.PureComponent<P, S={} SS={}>
React.PureComponent是有第三个参数的，它表示getSnapshotBeforeUpdate的返回值。

PureComponent中的shouldComponentUpdate 是由自身进行处理的，不需要我们自己处理，所以PureComponent可以在一定程度上提升性能。

*/

```typescript
// 定义组件
class MyComponent<P> extends React.Component<P> {
  internalProp: P;
  constructor(props: P) {
    super(props);
    this.internalProp = props;
  }
  render() {
    return (
    	 <span>hello world</span>
    );
  }
}

// 使用组件
type IProps = { name: string; age: number; };

<MyComponent<IProps> name="React" age={18} />;          // Success
<MyComponent<IProps> name="TypeScript" age="hello" />;


```

除此之外，函数类型还可以使用React.FunctionComponent<P={}>来定义，也可以使用其简写React.FC<P={}>，两者效果是一样的。它是一个泛型接口，可以接收一个参数，参数表示props的类型，这个参数不是必须的。它们就相当于这样：

//当使用这种形式来定义函数组件时，props中默认会带有children属性
type React.FC<P = {}> = React.FunctionComponent<P>

使用 React.FC 声明函数组件和普通声明的区别如下：

React.FC 显式地定义了返回类型，其他方式是隐式推导的；
React.FC 对静态属性：displayName、propTypes、defaultProps 提供了类型检查和自动补全；
React.FC 为 children 提供了隐式的类型（ReactElement | null）


```typescript
const MyComponent = <P>(props: P)=>{
// create
  return (
  	<span>
    	{props}
    </span>
  );
}
```

```typescript
//必须使用extends关键字来定义泛型参数才能被成功解析：

const MyComponent = <P extends any>(props: P)=>{
  return (
  	<span>
    	{props}
    </span>
  );
}


1. JSX.Element
先来看看JSX.Element类型的声明：
declare global {
  namespace JSX {
    interface Element extends React.ReactElement<any, any> { }
  }
}
/*
复制代码
可以看到，JSX.Element是ReactElement的子类型，它没有增加属性，两者是等价的。也就是说两种类型的变量可以相互赋值。
​
*/
JSX.Element 可以通过执行 React.createElement 或是转译 JSX 获得：
const jsx = <div>hello</div>
const ele = React.createElement("div", null, "hello");

```






















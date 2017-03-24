---
layout: post
title:  "Simple Pattern for Asynchronously Loading React Components"
date:   2017-03-20 16:16:01 -0600
categories: react patterns
---

Lets load a react component and it's dependencies. Make sure you're using Webpack 2.0+ or none of this will work.

First we will create a component called `LoadComponent` that will be responsible for... loading the component.


{% highlight js %}
import React from 'react'

class LoadComponent extends React.Component {
  state = {
    component: null,
    error: null
  }
  componentWillMount() {
    System.import(`./components/${this.props.component}`)
      .then(Component => {
        this.setState({component:Component.default})
      })
      .catch(error => {
        this.setState({error})
      })
  }
  render() {
    const {component, error} = this.state
    return this.props.render(component, error)
  }
}

export default LoadComponent
{% endhighlight %}

Two interesting things are happening:

1) We're using `System.import` which tells Webpack to create a separate bundle for the component we're importing and it's dependencies. So at this point we have our primary code bundle and a separate bundle for the imported module and it's dependencies ([code splitting](https://webpack.js.org/guides/code-splitting-import/#components/sidebar/sidebar.jsx)). If you clicked on the link you probably noticed `System.import` got updated to simply `import` but I'm going to use the old syntax because my Babel config doesn't seem to like the new syntax to my knowledge the old syntax is still supported and works the exact same way.

2) In the render method we're returning a function called render and passing in `LoadComponent`'s state as arguments. This pattern is called render props and has proven to be pretty useful in loads of day to day situations. Also gives the component a really nice declarative API so lets check that out:

{% highlight js %}
import React from 'react'
import LoadComponent from './LoadComponent'

class App extends React.Component {
  state = {
    showChart: false
  }
  showChart = () => {
    this.setState({
      showChart: !this.state.showChart
    })
  }
  render() {
    return (
      <div>
        <button onClick={this.showChart}>load chart</button>
        {this.state.showChart ? (
          <LoadComponent component='Chart.js' render={(Chart, error)=> (
            error ? (
              <p>Reload or something...</p>
            ) : (
              Chart ? <Chart /> : <div>Loading...</div>
            )
          )}/>
        ) : null}
      </div>
    )
  }
}
{% endhighlight %}

So `LoadComponent` takes two props:
 
 1) `component` - the name of the component it's responsible for rendering 
 
 2) `render` - the function that takes 




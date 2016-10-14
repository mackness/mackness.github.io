---
layout: post
title:  "React Reddit Client v.s. Mithril Reddit Client"
date:   2016-10-04
categories: mithril react webpack
---

I like React. I like Mithril. I wanted to compare them to figure out which one made me happier. This comparison will not take a scientific approach to measuring performance. Rather, it's designed to measure general developer happiness. I'll go over some of the things that made me happy and some of the pain points with both systems.

## Mithril

<p data-height="265" data-theme-id="light" data-slug-hash="vXAwAX" data-default-tab="js,result" data-user="mackness" data-embed-version="2" class="codepen">See the Pen <a href="https://codepen.io/mackness/pen/vXAwAX/">mithril reddit</a> by mackness (<a href="http://codepen.io/mackness">@mackness</a>) on <a href="http://codepen.io">CodePen</a>.</p>
<script async src="//assets.codepen.io/assets/embed/ei.js"></script>


I look at Mithril as a small no frills MVC framework. It seems extremely light and doesn't feel like there is a ton of magic going on behind the scenes. It makes me nervous when there's a lot going on in the background that I can't explain to myself so Mithril's simplicity is definitely refreshing. 

I'm gonna flat out list pros and cons.

### Pros:

 - Mithril has really convenient built in routing and request objects ([m.request](http://mithril.js.org/mithril.request.html) and [m.route](http://mithril.js.org/mithril.route.html)). It felt great to be able to use those two things without having to install a dependency. 
 - [m()](http://mithril.js.org/mithril.html) template system is great once you get used to it. Again, out of the box this just works without having to configure a transpiler or add another dependency.
 - Module structure is really simple. It's just an object with a controller and view property. Looks like this:
{% highlight js %}
var Component = {
    //controller
    controller: function() {},
    //view
    view: function(controller) {}
};
{% endhighlight %}

### Cons:
- Small community.
- No browser based dev tools :(


## React

<p data-height="265" data-theme-id="light" data-slug-hash="RGJmXV" data-default-tab="js,result" data-user="mackness" data-embed-version="2" class="codepen">See the Pen <a href="https://codepen.io/mackness/pen/RGJmXV/">react reddit</a> by mackness (<a href="http://codepen.io/mackness">@mackness</a>) on <a href="http://codepen.io">CodePen</a>.</p>
<script async src="//assets.codepen.io/assets/embed/ei.js"></script>




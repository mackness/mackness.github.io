---
layout: post
title:  "My Git Aliases"
date:   2016-11-17	 20:57:02 -0700
categories: git
---

## ~ /.gitconfig

{% highlight bash %}
[alias]
    s = status
    r = reset
    alias = config --get-regexp ^alias\\.
    a = !git add . && git status
    aa = !git add . && git add -u . && git status
    ac = !git add . && git commit
    au = !git add -u . && git status
    acm = !git add . && git commit -m
    c = commit
    cm = commit -m
    ca = commit --amend
    d = diff --color-words
    dh = diff --color-words head
    l = log --pretty=format:\"%C(yellow)%h%Cred%d\\ %Creset%s%Cblue\\ [%cn]\" --decorate
    ld = log --pretty=format:\"%C(yellow)%h\\ %ad%Cred%d\\ %Creset%s%Cblue\\ [%cn]\" --decorate --date=short
    lf = log --name-status --oneline
    lt = log --graph --decorate --pretty=oneline --abbrev-commit
    ll = log --stat --abbrev-commit
    lld = log --stat --abbrev-commit --date=short
    llt = log --graph --decorate --pretty=oneline --abbrev-commit
    master = checkout master
    spull = svn rebase
    spush = svn dcommit
    tree = log --graph --decorate --pretty=oneline --abbrev-commit
{% endhighlight %}


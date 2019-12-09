# Spring State Machine Introduction

## 介绍 Introduction

Spring Statemachine (SSM) 让程序开发者可以将传统的状态机概念应用到 Spring 程序中。SSM 提供了如下特性：

- 为简单使用场景提供的易用的 flate (one-level) 状态机
- 通过分级状态机结构 (Hierarchical state machine structure) 简化复杂的状态配置
- 通过状态机域 (State machine regions) 满足复杂的状态配置
- Triggers, transitions, guards, actions 的应用
- 类型安全的配置适配器
- 状态机事件监听者
- 通过 Spring IoC 集成 bean 到状态机中

在继续下文之前，建议通过查看术语表附录、参加状态机入门课程了解状态机的一般概念。本文档的剩余章节希望读者能够熟悉状态机的概念。

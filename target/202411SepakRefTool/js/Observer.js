// Observer.js
export default class Observer {
    constructor() {
      this.observers = [];
    }
  
    // オブザーバーを追加
    attach(observer) {
      this.observers.push(observer);
    }
  
    // オブザーバーに通知
    notifyObservers() {
      for (const observer of this.observers) {
        observer.update(this);
      }
    }
  }
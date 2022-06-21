# GuavaRateLimiter限流

## 一、限流使用场景

## 二、GuavaRateLimiter使用

### 1、限流

### 2、预热

## 三、优缺点及适用场景

### 1、限流

### 2、预热

## 四、GuavaRateLimiter源码分析

### 1. 限流

#### 1.1 初始化

#### 1.2 限流

```java
public boolean tryAcquire(int permits, long timeout, TimeUnit unit) {
    // 计算超时时间
    long timeoutMicros = max(unit.toMicros(timeout), 0);
    checkPermits(permits);
    long microsToWait;
    synchronized (mutex()) {
      // 算出当次请求距离系统启动的时间差
      long nowMicros = stopwatch.readMicros();
      if (!canAcquire(nowMicros, timeoutMicros)) {
        return false;
      } else {
        // 需要阻塞的时间
        microsToWait = reserveAndGetWaitLength(permits, nowMicros);
      }
    }
    stopwatch.sleepMicrosUninterruptibly(microsToWait);
    return true;
  }
```

```java
 
 final long reserveAndGetWaitLength(int permits, long nowMicros) {
    // 下次可用时间
    long momentAvailable = reserveEarliestAvailable(permits, nowMicros);
    // 可用时间 - 当前时间 = 需要睡眠的时间。
    // <0 说明不需要睡眠, 返回睡眠时间0
    return max(momentAvailable - nowMicros, 0);
 }
```

```java
 @Override
 
  final long reserveEarliestAvailable(int requiredPermits, long nowMicros) {
    // 重置 下次请求可取时间差 和 本周期剩余可取许可数
    resync(nowMicros);
    long returnValue = nextFreeTicketMicros;
    // 使用 掉的 许可次数
    double storedPermitsToSpend = min(requiredPermits, this.storedPermits);
    // 计算最大等待时长。 freshPermits 一定 >= 0
        // 如果请求的许可数 > 可用许可数     storedPermitsToSpend = 可用许可数     freshPermits > 0
        // 如果请求的许可数 < 可用许可数     storedPermitsToSpend = 请求的许可数   freshPermits = 0
    double freshPermits = requiredPermits - storedPermitsToSpend;
    long waitMicros =
        storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend)
            + (long) (freshPermits * stableIntervalMicros);

    this.nextFreeTicketMicros = LongMath.saturatedAdd(nextFreeTicketMicros, waitMicros);
    // 重算剩余的 许可数
    this.storedPermits -= storedPermitsToSpend;
    return returnValue;
  }
```

```java

  /** Updates {@code storedPermits} and {@code nextFreeTicketMicros} based on the current time. */
  void resync(long nowMicros) {
    // if nextFreeTicket is in the past, resync to now
    // nextFreeTicketMicros 算出来的下一次可用的时间
    // 当前时间 > 原本计算的可用时间, 说明竞争不是那么激烈，在当前周期内，我们可以多放几个请求过来
   
    if (nowMicros > nextFreeTicketMicros) {
    
       // 计算可以多放的请求个数 ：(nowMicros - nextFreeTicketMicros) / coolDownIntervalMicros();
       // (nowMicros - nextFreeTicketMicros) 表示时间差值, 比如当前时间为1000,原计算的请求时间为800。那么我们可以算出200的时间差
       //  coolDownIntervalMicros() : 根据周期请求频次算出每个频次的差距时间。比如qps=5,那么每次请求的间隔时间为200ms
       //  根据以上场景可求得 newPermits = 1; 即本周期我可以多放一个请求过来
      double newPermits = (nowMicros - nextFreeTicketMicros) / coolDownIntervalMicros();
      // storedPermits 为本周期剩余请求, 本周期最大请求 与 newPermits 取min 得到新的本周期可使用请求数
      storedPermits = min(maxPermits, storedPermits + newPermits);
      nextFreeTicketMicros = nowMicros;
    }
  }
```

### 2. 预热

#### 2.1 初始化

#### 2.2 限流
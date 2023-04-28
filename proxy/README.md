# Proxy

## 1 Shadowsocks Proxy

### Server side

- apt-get install python-pip
- pip install shadowsocks
- sudo ssserver -p 443 -k password -m aes-256-cfb --user nobody -d start

### Client side

- Install Shadowsocks Client, e.g. run `brew cask install shadowsocksx` in Mac OSX.
- SwitchSharp

## 2 Homebrew

- $ ALL_PROXY=socks5://127.0.0.1:9001 brew upgrade

## 3 git

For` https://` and `http://(e.g. http://github.com, https://github.com)`, run the following script.

- git config --global http.proxy 'socks5://127.0.0.1:1080'

For `git://`

- git config --global core.gitproxy 'socks5://127.0.0.1:1080'

For ssh (e.g. `git@github.com,ssh://git@github.com`)

- add ProxyCommand `nc -x localhost:1080 %h %p` to `~/.ssh/config` file.

To remove

- git config --global --unset http.proxy

## 4 ProxyChains

### Not work with OSX

- https://github.com/rofl0r/proxychains-ng/issues/78
- https://github.com/rofl0r/proxychains-ng/issues/210

```text
reboot
# Hold option Key
# press Command + R when system recovery screen presents.
# Choose language, click Terminal of Utilities from top bar
csrutil disable
reboot
```

## 5 Gradle

Not work with proxychains

- gradle -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7891 clean build
- ./gradlew -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7891 clean build

If this doesn't work, check your socks proxy port.

Another problem: JDK 11 Bug [No PSK available. Unable to resume](https://stackoverflow.com/questions/52574050/javax-net-ssl-sslexception-no-psk-available-unable-to-resume)

- gradle -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7890 -Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2 clean build

## 6 Maven

Almost the same with gradle

- mvn clean install -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7891
- ./mvnw clean install -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7891

## 7 Clash

API Dashboard (clone and npm start locally):

https://github.com/Dreamacro/clash-dashboard

API can be used directly, API docs:

- https://clash.gitbook.io/doc/restful-api/proxies

Get all proxies information:

- curl localhost:9090/proxies

Switch proxy:

- curl -X PUT localhost:9090/proxies/Proxy -d '{"name":"[中转]香港 线路① V2"}'
- curl -X PUT localhost:9090/proxies/Proxy -d '{"name":"中转|A|香港①|✨"}'

setup detect best node automatically, in `xx.yaml` config file, add the following to `proxy-groups`:

```text
  - 
    name: Auto
    type: url-test
    proxies:
      - '深港专线[Trojan][倍率:2.5]'
      - '深港专线2[Trojan][倍率:2.5]'
      - '深港专线3[Trojan][倍率:2.5]'
      - '深港专线6[Trojan][倍率:2.5]'
      - '深港专线7[Trojan][倍率:2.5]'
      - '深港专线8[倍率:2.5]'
      - '深港专线转香港BGP[M][倍率:2.5]'
      - '深港专线转香港BGP2[M][倍率:2.5]'
      - '深港专线转香港BGP3[M][Trojan][倍率:2.5]'
      - '深港专线转香港BGP4[M][Trojan][倍率:2.5]'
      - '深港专线转香港BGP7[M][Trojan][倍率:2.5]'
      - '深港专线转香港BGP8[M][Trojan][倍率:2.5]'
      - '深港专线转香港BGP9[M][倍率:2.5]'
    url: http://www.gstatic.com/generate_204
    interval: 60
```

## 8 Wget 

- `http_proxy=127.0.0.1:7890 wget http://www.example.com/`
- `https_proxy=127.0.0.1:7890 wget https://www.example.com/`

## 9 NPM

- npm install hexo-cli -g --registry https://registry.npm.taobao.org/

## 10 SSH

Recording to [SO_Thread - Connect with SSH through a proxy](https://stackoverflow.com/a/19162114/9304616), specify ssh config as follows:

```text
Host archat-api-dev
    HostName a.b.c.d
    ProxyCommand nc -X connect -x 127.0.0.1:7890 %h %p
    User ec2-user
    Port 22
```

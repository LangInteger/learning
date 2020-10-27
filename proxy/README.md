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

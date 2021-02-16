# Troubleshooting

## Cannot Open Elaina

Make sure you are running Java SE Version 11 or above. You can check your Java version in a terminal as follows:

```bash
$ java --version
java 11.0.9 2020-10-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.9+7-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.9+7-LTS, mixed mode)
```

## Elaina Shows Blank Screen

Make sure your are not running on a Mac VM. If you are, try disabling hardware acceleration.

References:

- <http://mail.openjdk.java.net/pipermail/openjfx-dev/2016-April/018974.html>
- <https://communities.vmware.com/t5/VMware-Fusion-Discussions/JavaFX-8-apps-don-t-render-correctly-under-OS-X-10-10-and-10-11/m-p/2215478>
- <https://stackoverflow.com/questions/18754803/how-to-disable-or-bypass-hardware-graphics-accelerationprism-in-javafx>

## Links Do Not Work

If you are running Elaina on Linux, it is possible that your system is missing some libraries required. Try installing `libgnome` and see if the problem is solved.

## Other Problems

If you encounter any problems while using Elaina, feel free to contact our developers via [GitHub Issue](https://github.com/lirc572/ip/issues/new/choose)!

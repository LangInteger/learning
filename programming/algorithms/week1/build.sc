import mill._, scalalib._

object unionfind extends JavaModule {
    def unmanagedClasspath = T {
        if (!os.exists(millSourcePath / "lib")) Agg()
        else Agg.from(os.list(millSourcePath / "lib").map(PathRef(_)))
    }
}
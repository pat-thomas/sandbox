(defproject spotify-client-playground "0.1.0-SNAPSHOT"
  :description  "FIXME: write description"
  :url          "http://example.com/FIXME"
  :license      {:name "Eclipse Public License"
                 :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main         spotify-client-playground.core/run-app
  :dependencies [[org.clojure/clojure        "1.6.0"]
                 [compojure                  "1.1.8"]
                 [http-kit                   "2.1.16"]
                 [org.clojure/tools.nrepl    "0.2.3"]
                 [com.cognitect/transit-cljs "0.8.188"]

                 [org.clojure/clojurescript  "0.0-2311"]
                 [org.clojure/core.async     "0.1.267.0-0d7780-alpha"]
                 [om                         "0.6.5"]
                 [om-sync                    "0.1.1"]
                 [secretary                  "1.2.0"]]
  :plugins      [[lein-cljsbuild "1.0.3"]]
  :source-paths ["src"]
  :cljsbuild    {:builds [{:id           "spotify_client"
                           :source-paths ["src/spotify_client_playground/app"]
                           :compiler     {:output-to     "resources/public/js/compiled/spotify_client_playground.js"
                                          :output-dir    "resources/public/js/compiled/out"
                                          :optimizations :none
                                          :source-map    true}}]})
(ns spotify-client-playground.app.core
  (:require [spotify-client-playground.app.xhr :as xhr]
            [cognitect.transit                 :as transit]
            [goog.dom                          :as gdom]
            [om.core                           :as om  :include-macros true]
            [om.dom                            :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {}))

(def r (transit/reader :json))

(defn parse-json-resp
  [resp]
  (transit/read r resp))

(defn spotify-search-xhr
  [search-term]
  (let [search-url (str "search?term=" search-term)]
    (xhr/do-xhr {:method      :get
                 :url         search-url
                 :on-complete (fn [resp]
                                (let [parsed-resp (parse-json-resp resp)
                                      items       (get-in parsed-resp ["result-set" "artists" "items"])
                                      artist-names (->> items
                                                        (mapv (fn [item]
                                                                (get item "name")))
                                                        sort)]
                                  (def aa artist-names)
                                  (swap! app-state assoc-in [:search :results] artist-names)))})))

(defn main-app
  [app owner opts]
  (reify
    om/IDisplayName
    (display-name [_]
      (or (:react-name opts) "App"))

    om/IRender
    (render [this]
      (dom/div nil
               (dom/input #js {:id "search-input"} nil)
               (dom/button #js {:onClick (fn [e]
                                           (let [search-term-val (.-value (.getElementById js/document "search-input"))]
                                             (spotify-search-xhr search-term-val)))}
                           "Search")
               (when-let [search-results (get-in app [:search :results])]
                 (dom/ul nil
                         (mapv (fn [res]
                                 (dom/li #js {:onClick   (fn [e]
                                                           (println "clicked!"))
                                              :className "result-list-item"}
                                         res))
                               (get-in app [:search :results]))))))))

(let [target (gdom/getElement "app-container")]
  (om/root main-app
           app-state
           {:target target
            :opts   {}}))





(ns saber-rattler.pitching-data
  (:require [clojure.string :as string]
            [net.cgrand.enlive-html :as html])
  (:use [incanter.core])
  (:import [java.net URL]))

(defn get-game-logs [url]
  (let [html  (html/html-resource (URL. url))
        table (html/select html [:table.table])
        raw   (->> (html/select table [:tr])
                   (map #(html/select % [:td]))
                   (map #(map html/text %)))
        data  (filter seq raw)]
    (remove (fn [col] (= "Game" (first col))) data)))

(defn to-keyword [input]
  (-> input
      string/lower-case 
      (string/replace \space \-)
      keyword))

(defn get-pitcher-repertoire [url]
  (let [html  (html/html-resource (URL. url))
        table (html/select html [:table.table])
        data  (->> (html/select table [:thead :tr :th])
                   (map html/text))]
    (rest (map to-keyword data))))

(defn- group-by-pitch [repertoire log]
  (let [game            (first log)
        pitches-by-game (rest log)
        groupings       (partition 2 2 (interleave repertoire pitches-by-game))]
    (map #(cons game %) groupings)))
  
(defn game-log-converter [game-logs pitcher-repertoire]
  (let [pitch-categorizer (partial group-by-pitch pitcher-repertoire)]       
    (map pitch-categorizer game-logs)))

(defn get-outcome-metric [url]
  (let [html  (html/html-resource (URL. url))
        table (html/select html [:table.table])
        data  (->> (html/select table [:thead :tr :th])
                   (map html/text))]
    (rest data)))

(defn get-pitch-outcomes [url]
  (let [html  (html/html-resource (URL. url))
        table (html/select html [:table.table])
        raw   (->> (html/select table [:tr])
                   (map #(html/select % [:td]))
                   (map #(map html/text %)))]
    (filter seq raw)))

(defn- group-by-metric [metrics stats]
  (let [pitch     (first stats)
        stat-data (rest stats)
        groupings (partition 2 2 (interleave metrics stat-data))]
    (map #(cons (to-keyword pitch) %) groupings)))

(defn pitch-matrics-converter [stats metrics]
  (let [metrics-categorizer (partial group-by-metric metrics)]       
    (map metrics-categorizer stats)))

;; TODO: lots of duplication above refactor
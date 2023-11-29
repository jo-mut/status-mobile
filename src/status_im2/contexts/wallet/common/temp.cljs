(ns status-im2.contexts.wallet.common.temp
  (:require
    [clojure.string :as string]
    [quo.foundations.resources :as quo.resources]
    [react-native.core :as rn]
    [status-im2.common.resources :as status.resources]
    [status-im2.constants :as constants]
    [utils.i18n :as i18n]))

(defn wallet-overview-state
  [networks]
  {:state             :default
   :time-frame        :none
   :metrics           :none
   :balance           "€0.00"
   :date              "20 Nov 2023"
   :begin-date        "16 May"
   :end-date          "25 May"
   :currency-change   "€0.00"
   :percentage-change "0.00%"
   :networks          networks})

(def tokens
  [{:token               :snt
    :state               :default
    :status              :empty
    :customization-color :blue
    :values              {:crypto-value      "0.00"
                          :fiat-value        "€0.00"
                          :percentage-change "0.00"
                          :fiat-change       "€0.00"}}
   {:token               :eth
    :state               :default
    :status              :empty
    :customization-color :blue
    :values              {:crypto-value      "0.00"
                          :fiat-value        "€0.00"
                          :percentage-change "0.00"
                          :fiat-change       "€0.00"}}
   {:token               :dai
    :state               :default
    :status              :empty
    :customization-color :blue
    :values              {:crypto-value      "0.00"
                          :fiat-value        "€0.00"
                          :percentage-change "0.00"
                          :fiat-change       "€0.00"}}])

(def address "0x39cf6E0Ba4C4530735616e1Ee7ff5FbCB726fBd4")

(def data-item-state
  {:description         :default
   :icon-right?         true
   :right-icon          :i/options
   :card?               true
   :label               :none
   :status              :default
   :size                :default
   :title               "Address"
   :customization-color :yellow})

(def account-origin-state
  {:type            :default-keypair
   :stored          :on-keycard
   :profile-picture (status.resources/get-mock-image :user-picture-male5)
   :derivation-path (string/replace constants/path-default-wallet #"/" " / ")
   :user-name       "Alisher Yakupov"
   :on-press        #(js/alert "pressed")})

(defn dapps-list
  [{:keys [on-press-icon]}]
  [{:dapp          {:avatar (quo.resources/get-dapp :coingecko)
                    :name   "Coingecko"
                    :value  "coingecko.com"}
    :state         :default
    :action        :icon
    :on-press-icon on-press-icon}
   {:dapp          {:avatar (quo.resources/get-dapp :uniswap)
                    :name   "Uniswap"
                    :value  "uniswap.org"}
    :state         :default
    :action        :icon
    :on-press-icon on-press-icon}])

(def other-accounts
  [{:customization-color :flamingo
    :emoji               "🍿"
    :name                "New House"
    :address             "0x21af6E0Ba4C4530735616e1Ee7ff5FbCB726f493"
    :networks            [{:network-name :ethereum :short-name "eth"}
                          {:network-name :optimism :short-name "opt"}]}
   {:customization-color :blue
    :emoji               "🎮"
    :name                "My savings"
    :address             "0x43cf6E0Ba4C4530735616e1Ee7ff5FbCB726f98d"
    :networks            [{:network-name :ethereum :short-name "eth"}]}])

(def asset-snt
  {:size       24
   :type       :token
   :token-name "SNT"
   :amount     1500
   :token-logo (quo.resources/get-token :snt)})

(def piggy-bank
  {:size         24
   :type         :account
   :account-name "Piggy bank"
   :emoji        "🐷"})

(def aretha-gosling
  {:size            24
   :type            :default
   :full-name       "Aretha Gosling"
   :profile-picture (status.resources/mock-images :user-picture-female2)})

(def mainnet
  {:size         24
   :type         :network
   :network-logo (quo.resources/get-network :ethereum)
   :network-name "Mainnet"})

(def activity-list
  [{:date              "Today"
    :transaction       :send
    :timestamp         "Today 22:20"
    :status            :pending
    :counter           1
    :first-tag         asset-snt
    :second-tag-prefix :t/from
    :second-tag        piggy-bank
    :third-tag-prefix  :t/to
    :third-tag         aretha-gosling
    :fourth-tag-prefix :t/via
    :fourth-tag        mainnet
    :blur?             false}
   {:date              "Yesterday"
    :transaction       :receive
    :timestamp         "Yesterday 22:20"
    :status            :pending
    :counter           1
    :first-tag         asset-snt
    :second-tag-prefix :t/from
    :second-tag        piggy-bank
    :third-tag-prefix  :t/to
    :third-tag         aretha-gosling
    :fourth-tag-prefix :t/via
    :fourth-tag        mainnet
    :blur?             false}])

(def collectible-list
  [(status.resources/get-mock-image :collectible1)
   (status.resources/get-mock-image :collectible2)
   (status.resources/get-mock-image :collectible3)
   (status.resources/get-mock-image :collectible4)])

(def buy-tokens-list
  [{:title             "Ramp"
    :description       :text
    :description-props {:text (i18n/label :t/ramp-description)}
    :tag               :context
    :tag-props         {:icon    :i/fees
                        :context "0.49% - 2.9%"}
    :action            :arrow
    :action-props      {:alignment :flex-start
                        :icon      :i/external}
    :image             :icon-avatar
    :image-props       {:icon (status.resources/get-service-image :ramp)}
    :on-press          #(rn/open-url "https://ramp.com")}
   {:title             "MoonPay"
    :description       :text
    :description-props {:text (i18n/label :t/moonpay-description)}
    :tag               :context
    :tag-props         {:icon    :i/fees
                        :context "1% - 4.5%"}
    :action            :arrow
    :action-props      {:alignment :flex-start
                        :icon      :i/external}
    :image             :icon-avatar
    :image-props       {:icon (status.resources/get-service-image :moonpay)}
    :on-press          #(rn/open-url "https://moonpay.com")}
   {:title             "Latamex"
    :description       :text
    :description-props {:text (i18n/label :t/latamex-description)}
    :tag               :context
    :tag-props         {:icon    :i/fees
                        :context "1% - 1.7%"}
    :action            :arrow
    :action-props      {:alignment :flex-start
                        :icon      :i/external}
    :image             :icon-avatar
    :image-props       {:icon (status.resources/get-service-image :latamex)}
    :on-press          #(rn/open-url "https://latamex.com")}])

(defn bridge-token-list
  [networks-list]
  [{:token               (quo.resources/get-token :snt)
    :label               "Status"
    :token-value         "0.00 SNT"
    :fiat-value          "€0.00"
    :networks            networks-list
    :state               :default
    :customization-color :blue}
   {:token               (quo.resources/get-token :eth)
    :label               "Ethereum"
    :token-value         "0.00 ETH"
    :fiat-value          "€0.00"
    :networks            networks-list
    :state               :default
    :customization-color :blue}
   {:token               (quo.resources/get-token :dai)
    :label               "Dai"
    :token-value         "0.00 DAI"
    :fiat-value          "€0.00"
    :networks            networks-list
    :state               :default
    :customization-color :blue}])

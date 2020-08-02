# SPRING REST CLIENT 02 - 커스터마이징

* RestTemplate

  * 기본적으로 java.net.HttpURLConnection 사용

  * 커스터마이징

    * 로컬 커스터마이징

    * 글로벌 커스터마이징

      * RestTemplateCostomizer

      * 빈 재정의

        ```java
        @Bean
        public RestTemplateCustomizer restTemplateCustomizer(){
            return new RestTemplateCustomizer() {
                @Override
                public void customize(RestTemplate restTemplate) {
                    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                }
            };
        }
        ```

        

* WebClient

  * 기본으로 Reactor Netty의 HTTP 클라이언트 사용.
  * 커스터마이징
    * 로컬 커스터마이징
    * 글로벌 커스터마이징
      * WebClientCustomizer
      * 빈 재정의
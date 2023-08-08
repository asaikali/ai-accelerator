# Spring AI Accelerator

This accelerator creates a starter spring application configured to talk ot 
a large language model, using the spring-ai library. The starter application
demonstrates best practices for working with AI services in a Spring Boot 
application.

## Enable Application Accelerator
The first step is to enable the application accelerator capability
in Azure Spring Apps Enterprise Tier. You can use the command below
replace the `ASA_SERVICE_RG` and `ASA_SERVICE_NAME` environment
variables with the values for the resource group and service name
where Azure Spring Apps is running.

```shell
ASA_SERVICE_RG=demo && \
ASA_SERVICE_NAME=demo-asa && \
az spring application-accelerator create \
  --resource-group ${ASA_SERVICE_RG} \
  --service ${ASA_SERVICE_NAME}
```

## Register the Accelerator
The second step is to register the accelerator with Azure Spring Apps
using the command below.You will need to make modifications to match
where the Azure Spring Apps instance is deploy, along with the specific
details of accelerator such as name, git repo it is located in, and
display name.
```shell
ASA_SERVICE_RG=demo && \
ASA_SERVICE_NAME=demo-asa && \
az spring application-accelerator customized-accelerator create \
  --resource-group ${ASA_SERVICE_RG} \
  --service ${ASA_SERVICE_NAME} \
  --name spring-ai-accelerator \
  --display-name "Spring AI App" \
  --git-branch springone \
  --git-interval 10 \
  --git-url  https://github.com/asaikali/ai-accelerator.git
```




This project contains a web service that will accept HTTP GET requests at
`http://localhost:8080/ai/simple`

There is optional `text` parameter whose default value is "Tell me a joke".

The response to the request is from the Azure OpenAI Service.

## Prerequisite

Before using the AI commands, obtain your Azure OpenAI `endpoint` and `api-key` from the Azure OpenAI Service section on [Azure Portal](https://portal.azure.com)

The Spring AI project defines a configuration property named `spring.ai.openai.api-key` that you should set to the value of the `API Key` obtained from `openai.com`.

Exporting an environment variables is one way to set these configuration properties.
```shell
export SPRING_AI_AZURE_OPENAI_API_KEY=<INSERT KEY HERE>
export SPRING_AI_AZURE_OPENAI_ENDPOINT=<INSERT ENDPOINT URL HERE>
```

## Building and running

```
./mvnw spring-boot:run
```

## Access the endpoint

To get a response to the default request of "Tell me a joke"

```shell
curl http://localhost:8080/ai/simple
```

A sample response is

```text
Why don't scientists trust atoms?

Because they make up everything!
```

Now using the `text` request parameter
```shell
curl --get  --data-urlencode 'text=Tell me a joke about a cow.' http://localhost:8080/ai/simple 
```
A sample response is

```text
Why did the cow go to outer space? To see the moooon!
```


{
	"info": {
		"_postman_id": "0d0c6e38-686e-4085-9adb-239f6866792f",
		"name": "momnkids",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "auth",
			"item": [
				{
					"name": "Register User",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"username\": \"{{newUsername}}_{{$timestamp}}\",\n    \"password\": \"{{newPassword}}\",\n    \"name\": \"{{newName}}\",\n    \"email\": \"{{$timestamp}}_{{newEmail}}\",\n    \"roles\": \"{{userRoles}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/auth/register",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"auth",
								"register"
							]
						}
					},
					"response": []
				},
				{
					"name": "Register Doctor",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"username\": \"{{newUsername}}_{{$timestamp}}\",\n    \"password\": \"{{newPassword}}\",\n    \"name\": \"{{newName}}\",\n    \"email\": \"{{$timestamp}}_{{newEmail}}\",\n    \"roles\": \"{{doctorRoles}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/auth/register",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"auth",
								"register"
							]
						}
					},
					"response": []
				},
				{
					"name": "Login User",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									"const host = pm.environment.get('host');",
									"const port = pm.environment.get('port');",
									"const protocol = pm.environment.get('protocol');",
									"",
									"const postRequest = {",
									"    url: `${protocol}://${host}:${port}/auth/register`,",
									"    method: 'POST',",
									"    header: {",
									"        'Content-Type': 'application/json',",
									"    },",
									"    body: {",
									"        mode: 'raw',",
									"        raw: JSON.stringify({",
									"            username: pm.environment.get('newUsername'),",
									"            password: pm.environment.get('newPassword'),",
									"            name: pm.environment.get('newName'),",
									"            email: pm.environment.get('newEmail'),",
									"        }),",
									"    },",
									"};",
									"",
									"pm.sendRequest(postRequest, (error, response) => {",
									"    console.log(error ? error : response.json());",
									"});"
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"var jsonData = pm.response.json();",
									"pm.environment.set(\"accessToken\", jsonData.data.token);",
									"pm.environment.set(\"refreshToken\", jsonData.data.refreshToken);"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"username\": \"{{newUsername}}\",\n    \"password\": \"{{newPassword}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/auth/login",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"auth",
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "Refresh token",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"refreshToken\": \"{{refreshToken}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/auth/refreshToken",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"auth",
								"refreshToken"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "pregnancy",
			"item": [
				{
					"name": "Prerequiresite",
					"item": [
						{
							"name": "Add user",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n    \"username\": \"{{newUsername}}_{{$timestamp}}\",\n    \"password\": \"{{newPassword}}\",\n    \"name\": \"{{newName}}\",\n    \"email\": \"{{$timestamp}}_{{newEmail}}\",\n    \"roles\": \"{{userRoles}}\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "{{protocol}}://{{host}}:{{port}}/auth/register",
									"protocol": "{{protocol}}",
									"host": [
										"{{host}}"
									],
									"port": "{{port}}",
									"path": [
										"auth",
										"register"
									]
								}
							},
							"response": []
						},
						{
							"name": "Login",
							"event": [
								{
									"listen": "prerequest",
									"script": {
										"exec": [
											"const host = pm.environment.get('host');",
											"const port = pm.environment.get('port');",
											"const protocol = pm.environment.get('protocol');",
											"",
											"const postRequest = {",
											"    url: `${protocol}://${host}:${port}/auth/register`,",
											"    method: 'POST',",
											"    header: {",
											"        'Content-Type': 'application/json',",
											"    },",
											"    body: {",
											"        mode: 'raw',",
											"        raw: JSON.stringify({",
											"            username: pm.environment.get('newUsername'),",
											"            password: pm.environment.get('newPassword'),",
											"            name: pm.environment.get('newName'),",
											"            email: pm.environment.get('newEmail'),",
											"        }),",
											"    },",
											"};",
											"",
											"pm.sendRequest(postRequest, (error, response) => {",
											"    console.log(error ? error : response.json());",
											"});"
										],
										"type": "text/javascript"
									}
								},
								{
									"listen": "test",
									"script": {
										"exec": [
											"var jsonData = pm.response.json();",
											"pm.environment.set(\"accessToken\", jsonData.data.token);",
											"pm.environment.set(\"refreshToken\", jsonData.data.refreshToken);"
										],
										"type": "text/javascript"
									}
								}
							],
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n    \"username\": \"{{newUsername}}\",\n    \"password\": \"{{newPassword}}\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "{{protocol}}://{{host}}:{{port}}/auth/login",
									"protocol": "{{protocol}}",
									"host": [
										"{{host}}"
									],
									"port": "{{port}}",
									"path": [
										"auth",
										"login"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Add Pregnancy",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{accessToken}}",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"hpht\": \"{{hpht}}\",\n    \"hpl\": \"{{hpl}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/api/pregnancies",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"api",
								"pregnancies"
							]
						}
					},
					"response": []
				},
				{
					"name": "Add Pregnancy (empty hpl)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{accessToken}}",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"hpht\": \"{{hpht}}\",\n    \"hpl\": null\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/api/pregnancies",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"api",
								"pregnancies"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Pregnancy",
					"request": {
						"method": "GET",
						"header": []
					},
					"response": []
				}
			]
		},
		{
			"name": "child",
			"item": [
				{
					"name": "Prerequiresite",
					"item": [
						{
							"name": "Add user",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n    \"username\": \"{{newUsername}}_{{$timestamp}}\",\n    \"password\": \"{{newPassword}}\",\n    \"name\": \"{{newName}}\",\n    \"email\": \"{{$timestamp}}_{{newEmail}}\",\n    \"roles\": \"{{userRoles}}\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "{{protocol}}://{{host}}:{{port}}/auth/register",
									"protocol": "{{protocol}}",
									"host": [
										"{{host}}"
									],
									"port": "{{port}}",
									"path": [
										"auth",
										"register"
									]
								}
							},
							"response": []
						},
						{
							"name": "Login",
							"event": [
								{
									"listen": "prerequest",
									"script": {
										"exec": [
											"const host = pm.environment.get('host');",
											"const port = pm.environment.get('port');",
											"const protocol = pm.environment.get('protocol');",
											"",
											"const postRequest = {",
											"    url: `${protocol}://${host}:${port}/auth/register`,",
											"    method: 'POST',",
											"    header: {",
											"        'Content-Type': 'application/json',",
											"    },",
											"    body: {",
											"        mode: 'raw',",
											"        raw: JSON.stringify({",
											"            username: pm.environment.get('newUsername'),",
											"            password: pm.environment.get('newPassword'),",
											"            name: pm.environment.get('newName'),",
											"            email: pm.environment.get('newEmail'),",
											"        }),",
											"    },",
											"};",
											"",
											"pm.sendRequest(postRequest, (error, response) => {",
											"    console.log(error ? error : response.json());",
											"});"
										],
										"type": "text/javascript"
									}
								},
								{
									"listen": "test",
									"script": {
										"exec": [
											"var jsonData = pm.response.json();",
											"pm.environment.set(\"accessToken\", jsonData.data.token);",
											"pm.environment.set(\"refreshToken\", jsonData.data.refreshToken);"
										],
										"type": "text/javascript"
									}
								}
							],
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n    \"username\": \"{{newUsername}}\",\n    \"password\": \"{{newPassword}}\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "{{protocol}}://{{host}}:{{port}}/auth/login",
									"protocol": "{{protocol}}",
									"host": [
										"{{host}}"
									],
									"port": "{{port}}",
									"path": [
										"auth",
										"login"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Add Child",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{accessToken}}",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"age\": \"{{age}}\",\n    \"length\": \"{{length}}\",\n    \"weight\": \"{{weight}}\",\n    \"illustration\": \"{{illustration}}\",\n    \"description\": \"{{description}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/api/childs",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"api",
								"childs"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Child",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{accessToken}}",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{protocol}}://{{host}}:{{port}}/api/childs",
							"protocol": "{{protocol}}",
							"host": [
								"{{host}}"
							],
							"port": "{{port}}",
							"path": [
								"api",
								"childs"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}
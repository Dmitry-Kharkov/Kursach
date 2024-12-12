import { defineStore } from "pinia";
import { DateTime } from 'luxon'
import { apolloClient } from '@/apollo'
import { mRefreshToken, mAuth, registration } from "@/apollo/mutates"

export const useAuth = defineStore("auth", () => {

  const register = async (username, password) => {
    const responseRegistration = (await apolloClient.mutate(registration(username, password, password)))
    if (!responseRegistration.data.register.success) {
      alert(`Произошла ошибка при регистрации: ${JSON.stringify(responseRegistration.data.register.errors)}`)
      return null;
    }
    return auth(username, password)
  }  
        
    async function currentToken() {
        if (localStorage.getItem('token') != undefined && localStorage.getItem('tokenExp') != undefined &&
          localStorage.getItem('refreshToken') != undefined && localStorage.getItem('refreshTokenExp') != undefined) {
          const token = {
            token: localStorage.getItem('token'),
            tokenExp: DateTime.fromISO(localStorage.getItem('tokenExp')),
            refreshToken: localStorage.getItem('refreshToken'),
            refreshTokenExp: DateTime.fromISO(localStorage.getItem('refreshTokenExp')),
          }
          if (token.tokenExp > DateTime.now()) {
            return token
          } else {
            const revokeRefreshToken = token.refreshTokenExp < DateTime.now()
            return await refreshToken(token.refreshToken, revokeRefreshToken)
          }
        }
        return null;
      }
    
      const auth = async (username, password) => {
        const response = (await apolloClient.mutate(mAuth(username, password)))
        if (!response.data.tokenAuth.success) {
          alert("Произошла ошибка при авторизации")
          return null;
        }
        localStorage.setItem('token', response.data.tokenAuth.token.token)
        localStorage.setItem('tokenExp', response.data.tokenAuth.token.payload.exp)
        localStorage.setItem('refreshToken', response.data.tokenAuth.refreshToken.token)
        localStorage.setItem('refreshTokenExp', response.data.tokenAuth.refreshToken.expiresAt)
        localStorage.setItem('userId', response.data.tokenAuth.user.id)
        return {
          token: response.data.tokenAuth.token.token,
          tokenExp: DateTime.fromISO(response.data.tokenAuth.token.payload.exp),
          refreshToken: response.data.tokenAuth.refreshToken.token,
          refreshTokenExp: DateTime.fromISO(response.data.tokenAuth.refreshToken.expiresAt),
        };
      }

      const refreshToken = async (refreshToken, revokeRefreshToken) => {
        const response = (await apolloClient.mutate(mRefreshToken(refreshToken, revokeRefreshToken)))
        if (response.data.refreshToken.success == false) {
          localStorage.removeItem('token')
          localStorage.removeItem('tokenExp')
          localStorage.removeItem('refreshToken')
          localStorage.removeItem('refreshTokenExp')
          return null;
        }
        console.log(response.data)
        localStorage.setItem('token', response.data.refreshToken.token.token)
        localStorage.setItem('tokenExp', response.data.refreshToken.token.payload.exp)
        localStorage.setItem('refreshToken', response.data.refreshToken.refreshToken.token)
        localStorage.setItem('refreshTokenExp', response.data.refreshToken.refreshToken.expiresAt)
        return {
          token: response.data.refreshToken.token.token,
          tokenExp: DateTime.fromISO(response.data.refreshToken.token.payload.exp),
          refreshToken: response.data.refreshToken.refreshToken.token,
          refreshTokenExp: DateTime.fromISO(response.data.refreshToken.refreshToken.expiresAt),
        };
      }    

    return {
        currentToken, auth, register
    }
})
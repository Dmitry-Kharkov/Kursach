import gql from "graphql-tag";

export const mAuth = (username, password) => ({
    mutation: gql`
        mutation auth($username: String!, $password: String!) {
            tokenAuth(username: $username, password: $password) {
                success
                errors
                token {
                    token
                    payload {
                        exp
                    }
                }
                refreshToken {
                    token
                    expiresAt
                }
                user {
                    id
                }                
            }
        }
    `,
    variables: {
        username,
        password
    }
});

export const mRefreshToken = (refreshToken, revokeRefreshToken = false) => ({
    mutation: gql`
        mutation refreshToken ($refreshToken: String!, $revokeRefreshToken: Boolean!) {
            refreshToken(refreshToken: $refreshToken, revokeRefreshToken: $revokeRefreshToken) {
                success
                errors
                token {
                token
                payload {
                    exp
                }
                }
                refreshToken {
                token
                expiresAt
                }
            }
        }
    `,
    variables: {
        refreshToken,
        revokeRefreshToken
    }
});



export const mAddApplication = (token, name, description, userId, needRoles, teamId) => ({
    mutation: gql`
        mutation createApplication ($input: ApplicationModelInput!){
            createApplication(input: $input) {
                ... on OperationInfo {
                    messages {
                        message
                    }
                }
                ... on ApplicationType {
                    id
                    team {
                        id,
                        name
                        members {
                        user {
                            id
                            username
                        }
                        role {
                            name
                        }
                    }                        
                    }
                    name
                    description
                    createdAt
                    completed
                    needRoles {
                        id
                        name
                    }
                    needRoles {
                        id
                        name
                    }
                }
            }
        }
    `,
    variables: {
        "input": {
            "name": name,
            "owner": {
                "id": userId
            },
            "needRoles": needRoles,
            "description": description,
            "team": {
                "id": teamId
            }
        }
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const mDeleteApplication = (token, applicationId) => ({
    mutation: gql`
        mutation deleteApplication($input: NodeInput!) {
            deleteApplication(input: $input) {
                ... on OperationInfo {
                    messages {
                    message
                    }
                }
                ... on ApplicationType {
                    id
                }
            }
        }
    `,
    variables: {
        "input": {
            "id": applicationId
        }
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const mCreateTeam = (token, name, description, userId, ) => ({
    mutation: gql`
        mutation createTeam($input: TeamModelInput!) {
            createTeam(input: $input) {
                ... on OperationInfo {
                messages {
                    message
                }
                }
                ... on TeamModelType {
                    id
                    name
                    description
                    createdAt
                    completed
                }
            }
        }
    `,
    variables: {
        "input": {
            "name": name,
            "owner": {
              "id": userId
            },
            "description": description
        }
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const enterToTeam = (token, teamId, userId, roleId) => ({
    mutation: gql`
        mutation createTeamMember($input: TeamMemberModelInput!) {
            createTeamMember(input: $input) {
                ... on TeamMemberType {
                    id
                }
                ... on OperationInfo {
                messages {
                    message
                }
                }
            }
        }
    `,
    variables: {
        "input": {
            "team": {
              "id": teamId
            },
            "user": {
              "id": userId
            },
            "role": {
              "id": roleId
            }
          }
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const mDeleteTeam = (token, teamId) => ({
    mutation: gql`
        mutation deleteTeam($input: NodeInput!) {
            deleteTeam(input: $input) {
                ... on TeamModelType {
                id
                }
                ... on OperationInfo {
                messages {
                    message
                }
                }
            }
        }
    `,
    variables: {
        "input": {
            "id": teamId
        }
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const mApplicationEnterToTeam = (token, description, applicationId, userId, roleId) => ({
    mutation: gql`
        mutation enterToTeam($input: ApplicationEnterTeamModelInput!) {
            createApplicationEnterTeam(input: $input) {
                ... on OperationInfo {
                messages {
                    message
                }
                }
                ... on ApplicationEnterTeamModelType {
                id
                }
            }
        }
    `,
    variables: {
        "input": {
          "description": description,
          "application": {
            "id": applicationId
          },
          "user": {
            "id": userId
          },
          "role": {
            "id": roleId
          }
        }
      },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const registration = (username, password1, password2) => ({
    mutation: gql`
        mutation registration ($username: String!, $password1: String!, $password2: String!){
            register(username: $username, password1: $password1, password2: $password2) {
                success
                errors
            }
        }
    `,
    variables: {
        username,
        password1,
        password2
    },
});

export const mRejectApllicationEnterToTeam = (token, applicationId) => ({
    mutation: gql`
        mutation deleteApplicationEnterTeam($input: NodeInput!) {
            deleteApplicationEnterTeam(input: $input) {
                ... on ApplicationEnterTeamModelType {
                    id
                }
                ... on OperationInfo {
                messages {
                    message
                }
                }
            }
        }
    `,
    variables: {
        "input": {
          "id": applicationId
        }
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const mUpdatePassword = (token, oldPassword, newPassword) => ({
    mutation: gql`
        mutation updatePassword($oldPassword: String!, $newPassword: String!) {
            updatePassword(oldPassword: $oldPassword, newPassword: $newPassword) {
                username
            }
        }
    `,
    variables: {
        oldPassword,
        newPassword
    },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});

export const mUpdateRoles = (token, userId, roles) => ({
    mutation: gql`
        mutation updateAccount ($input: UserModelInputPartial!) {
            updateAccount(input: $input) {
                ... on UserModelType {
                roles {
                    id
                    name
                }
                }
                ... on OperationInfo {
                messages {
                    message
                }
                }
            }
        }
    `,
    variables: {
        "input": {
          "id": userId,
          "roles": roles
        }
      },
    context: {
        headers: {
          Authorization: `JWT ${token}`
        }
      }    
});
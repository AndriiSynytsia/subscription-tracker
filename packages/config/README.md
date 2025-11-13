# @subscription-tracker/config

Shared TypeScript configuration for the subscription-tracker monorepo.

## Usage

Extend this configuration in your app's `tsconfig.json`:

```json
{
  "extends": "@subscription-tracker/config/tsconfig.base.json",
  "compilerOptions": {
    "baseUrl": ".",
    "paths": {
      "@/*": ["./src/*"]
    }
  },
  "include": ["next-env.d.ts", "**/*.ts", "**/*.tsx", ".next/types/**/*.ts"],
  "exclude": ["node_modules"]
}
```



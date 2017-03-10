/**
 * @author walid
 * @date 2017/3/10
 * @description 构建插件
 */

const yargs = require('yargs')
const argv = yargs.argv
const generator = require('./gen')

generator.generate()

// generator.generate(argv._[0])

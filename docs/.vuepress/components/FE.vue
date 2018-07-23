<template>
    <div class="fe">
        <div class="list" v-for="item in list">
            {{item.img}}
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            list: [
                {img : '1'},
                {img : '2'},
                {img : '3'}
            ]
        }
    }   
}
</script>

<style>
.c {
    color: red
}
</style>

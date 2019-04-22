
function getIndex(list, id) {
    for(var i=0; i<list.length; i++){
        if (list[i].id == id){
            window.alert(i+' id was '+id);
            return i;
        }
    }
    return -1;
}

var arrivalApi = Vue.resource('/arrival{/id}');

Vue.component('arrival-form', {
    props: ['arrivals', 'arrivalAttr'],
    data: function(){
        return{
            text: '',
            id: ''
        }
    },
    watch: {
        arrivalAttr: function(newVal, oldVal){
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template: '<div> ' +
        ' <input type="text" placeholder="Type something" v-model="text"/>' +
        ' <input type="button" value="Save" @click="save"/> ' +
        ' </div>',
    methods: {
        save: function(){
            var arrival = {text: this.text};
            if (this.id) {
                arrivalApi.update({id: this.id}, arrival).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.arrivals, data.id);
                        this.arrivals.splice(index, 1, data);
                        this.id='';
                        this.text='';
                    })
                )
            } else {
            arrivalApi.save({}, arrival).then( result =>
                result.json().then( data => {
                    this.arrivals.push(data);
                    this.text = '';
                })
            )}
        }
    }
})

Vue.component('arrival-row', {
    props: ['arrival', 'editMethod', 'arrivals'],
    template: '<div> ' +
        '<i>({{arrival.id}})</i> ' +
        '{{arrival.text}} ' +
        '<span style="position: absolute; right: 0px;">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="Delete" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.arrival);
        },
        del: function () {
            arrivalApi.remove({id: this.arrival.id}).then(result =>{
                if(result.ok){
                    this.arrivals.splice(this.arrivals.indexOf(this.arrival), 1);
            }
            })
        }
    }
});

Vue.component('arrivals-list', {
    data: function(){
        return {
            arrival: null
        }
    },
    props: ['arrivals'],
    template:
        '<div style="position: relative; width: 300px;">' +
            '<arrival-form :arrivals = "arrivals" :arrivalAttr="arrival"/>' +
            '<arrival-row  v-for="arrival in arrivals" :key="arrival.id" :arrival="arrival" ' +
        ' :editMethod="editMethod" :arrivals="arrivals"/>' +
        '</div>',

    methods: {
        editMethod: function (arrival) {
            this.arrival = arrival;
        }
        
    }
});

var app = new Vue({
    el: '#app',
    template:
            '<div>'+
                '<div v-if="!profile"> You have to authorize using <a href="/login"> GOOGLE</a></div>'+
                '<div v-else>' +
                    '<div>{{profile.name}}&nbsp;<a href="/logout">LOGOUT</a></div>'+
                    '<arrivals-list  :arrivals = "arrivals" />' +
                '</div>'+
            '</div>',
    data: {
        arrivals: frontendData.arrivals,
        profile: frontendData.profile
    },
    created: function () {
    //     arrivalApi.get().then(result =>
    //     result.json().then(data =>
    //     data.forEach(arrival => this.arrivals.push(arrival))
    // )
    // )
    }
});